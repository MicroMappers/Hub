package org.qcri.micromappers.controller;

import java.io.IOException;

import org.qcri.micromappers.entity.Collection;
import org.qcri.micromappers.models.CollectionTask;
import org.qcri.micromappers.service.CollectionService;
import org.qcri.micromappers.utility.CollectionStatus;
import org.qcri.micromappers.utility.GenericCache;
import org.qcri.micromappers.utility.ResponseWrapper;
import org.qcri.micromappers.utility.TwitterStreamTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kushal
 * RESTFul APIs to start and stop Twitter collections.
 */

@RestController
@RequestMapping("/twitter")
public class TwitterCollectionController extends BaseCollectionController {

	@Autowired
	private CollectionService collectionService;

	private GenericCache cache = GenericCache.getInstance();

	public ResponseWrapper startTask(CollectionTask task) {
		logger.info("Collection start request received for " + task.getCollectionCode());

		//check if all twitter specific information is available in the request
		if (!task.checkSocialConfigInfo()) {
			return new ResponseWrapper(null, false, CollectionStatus.FATAL_ERROR.toString(), 
					"One or more Twitter authentication token(s) are missing");
		}

		//check if all query parameters are missing in the query
		if (!task.isToTrackAvailable() && !task.isToFollowAvailable() && !task.isGeoLocationAvailable()) {
			return new ResponseWrapper(null, false, CollectionStatus.FATAL_ERROR.toString(), 
					"Missing one or more fields (toTrack, toFollow, and geoLocation). At least one field is required");
		}

		String collectionCode = task.getCollectionCode();

		//check if a task is already running with same configurations
		logger.info("Checking OAuth parameters for " + collectionCode);
		if (cache.isConfigExists(task)) {
			String msg = "Provided OAuth configurations already in use. Please stop this collection and then start again.";
			logger.info(collectionCode + ": " + msg);

			return new ResponseWrapper(null, false, CollectionStatus.FATAL_ERROR.toString(), msg);
		}

		logger.info("Initializing connection with Twitter streaming API for collection " + collectionCode);
		task.setStatusCode(CollectionStatus.RUNNING);

		try {
			TwitterStreamTracker tracker = new TwitterStreamTracker(task);
			tracker.start();

			String code = task.getCollectionCode();
			cache.incrCounter(code, 0L);

			// if twitter streaming connection successful then change the status code
			task.setStatusCode(CollectionStatus.RUNNING);
			task.setStatusMessage(null);
			cache.setTwitterTracker(code, tracker);
			cache.setTwtConfigMap(code, task);

			//Adding a new log to CollectionLog
			collectionLogService.addByCollectionCode(collectionCode);

			//Updating the status of collection in db
			collectionService.updateStatusByCode(code, CollectionStatus.RUNNING);
			return new ResponseWrapper(cache.getTwitterConfig(code), true, CollectionStatus.RUNNING.toString(), 
					CollectionStatus.RUNNING.toString());
		} catch (Exception ex) {
			logger.error("Exception in creating TwitterStreamTracker for collection " + collectionCode, ex);
			return new ResponseWrapper(null, false, CollectionStatus.FATAL_ERROR.toString(), ex.getMessage());
		}
	}

	protected ResponseWrapper stopTask(Long id) {
		Collection collection = collectionService.getById(id);
		String collectionCode = collection.getCode();
		CollectionTask task = null;

		TwitterStreamTracker tracker = cache.getTwitterTracker(collectionCode);

		if (tracker != null) {
			try {
				tracker.close();
				task = cache.getTwitterConfig(collectionCode);
				clearCache(collectionCode);
			} catch (IOException e) {
				task = cache.getTwitterConfig(collectionCode);
				clearCache(collectionCode);
				return new ResponseWrapper(task, true, CollectionStatus.NOT_RUNNING.toString(), e.getMessage());
			}
			logger.info(collectionCode + ": " + "Collector has been successfully stopped.");
		} else {
			task = cache.getTwitterConfig(collectionCode);
			clearCache(collectionCode);
			logger.info("No collector instances found to be stopped with the given id:" + collectionCode);
		}

		if (task != null) {
			return new ResponseWrapper(task, true, CollectionStatus.NOT_RUNNING.toString(), null);
		}
		return null;
	}

	private void clearCache(String collectionCode) {
		cache.delFailedCollection(collectionCode);
		cache.deleteCounter(collectionCode);
		cache.delTwtConfigMap(collectionCode);
		cache.delTwitterTracker(collectionCode);
		cache.delReconnectAttempts(collectionCode);
	}

}
