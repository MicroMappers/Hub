package org.qcri.micromappers.models;

import java.io.Serializable;
import java.util.List;

import org.qcri.micromappers.utility.Constants;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by jlucas on 12/21/16.
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 2254473661569318782L;

    private List<T> list;

    private int total = 0;

    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    @SuppressWarnings("unused")
    private int pageStart = 0;

    private int pages = 1;

    private int pageNumber = 1;

    private boolean isFirstPage = false;

    private boolean isLastPage = false;

    private boolean hasPreviousPage = false;

    private boolean hasNextPage = false;

    private int navigatePages = Constants.DEFAULT_NAVIGATION_PAGE_SIZE;

    private int[] navigatePageNumbers;

    public PageInfo(Page<T> pages){
        init(pages);
    }

    @JsonIgnore
    private void init(Page<T> pages) {

        this.total = (int)pages.getTotalElements();

        this.pages = (this.total - 1) / this.pageSize + 1;
       // this.pages = pages.getTotalPages();
        this.pageNumber = pages.getNumber() + 1;

        this.isFirstPage = pages.isFirst();
        this.isLastPage = pages.isLast();
        this.hasPreviousPage = pages.hasPrevious();
        this.hasNextPage = pages.hasNext();

        this.calcNavigatePageNumbers();

    }

    @JsonIgnore
    private void calcNavigatePageNumbers() {
        if (this.pages <= navigatePages) {
            navigatePageNumbers = new int[this.pages];
            for (int i = 0; i < this.pages; i++) {
                navigatePageNumbers[i] = i + 1;
            }
        } else {
            navigatePageNumbers = new int[navigatePages];
            int startNum = pageNumber - navigatePages / 2;
            int endNum = pageNumber + navigatePages / 2;

           // int startNum = pageNumber;
           // int endNum = pageNumber + navigatePages/2;

            if (startNum < 1) {
                startNum = 1;
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNumbers[i] = startNum++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatePageNumbers[i] = endNum--;
                }
            } else {
                for (int i = 0; i < navigatePages; i++) {
                    navigatePageNumbers[i] = startNum++;
                }
            }
        }
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public int getTotal() {
        return this.total;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPages() {
        return this.pages;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int[] getNavigatePageNumbers() {
        return this.navigatePageNumbers;
    }

    public void setNavigatePageNumbers(int[] navigatePageNumbers) {
        this.navigatePageNumbers = navigatePageNumbers;
    }

    public boolean isFirstPage() {
        return this.isFirstPage;
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }

    public boolean hasPreviousPage() {
        return this.hasPreviousPage;
    }

    public boolean hasNextPage() {
        return this.hasNextPage;
    }

    public int getPageStart() {
        return (pageNumber - 1) * pageSize;
    }

}
