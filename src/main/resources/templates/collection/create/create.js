$.ajaxSetup({
    headers: { 
        'X-CSRF-TOKEN': '${_csrf.token}',
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
});

$(document).ready(function() {
    $('#collectionCreate').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitButtons: '#collectionCreate button[type="submit"]',
        fields: {
            name: {
                trigger: "focus blur",
                validators: {
                    remote: {
                        url: '${rc.getContextPath()}/collection/existName',
                        data: function(validator, $field, value) {
                            return {
                                name: document.getElementsByName('name')[0].value
                            };
                        },
                        message: 'This collectionName is not available',
                        type: 'GET'
                    },
                    stringLength: {
                        min: 2,
                    }
                }
            },
            provider: {
                validators: {
                    remote: {
                        url: '${rc.getContextPath()}/user/isProviderConnected',
                        data: function(validator, $field, value) {
                            return {
                                provider: document.getElementsByName('provider')[0].value
                            };
                        },
                        message: 'Please connect your social accounts before creating collection. <a href="${rc.getContextPath()}/connect">Click here</a> to connect.',
                        type: 'GET'
                    }
                }
            }
        }        
    }).on('success.form.bv', function (e) {
        createCollection();
    });
    
    //Populating language dropdown
    var langSelect = $('#lang')[0];
    langSelect.add(new Option(LANGS[0][0], LANGS[0][1], true, true));
    for(i = 1; i < LANGS.length; i++) { 
        langSelect.add(new Option(LANGS[i][0], LANGS[i][1]));
    }
});

$('#provider').change(function() {
    if(document.getElementsByName('provider')[0].value == 'TWITTER'){
        $('#facebookConfigDiv').hide();
        $('#keywordsDiv').show();
    }else if(document.getElementsByName('provider')[0].value == 'FACEBOOK'){
        $('#facebookConfigDiv').show();
        $('#keywordsDiv').hide();
    }else{
        $('#facebookConfigDiv').show();
        $('#keywordsDiv').show();
    }
});

function createCollection() {
        
    //Getting the selected languages
    var langFilters = [];
    $("#lang :selected").each(function( index ) {
        if($( this ).val().trim() != ""){
            langFilters.push($( this ).val().trim());
        }
        
    });

    var eventTypeId = $("#eventTypeId").val();
    var eventType = $("#eventType").val();
    
    var data = {
            name: document.getElementsByName('name')[0].value.toLowerCase().trim(),
            code: document.getElementsByName('name')[0].value.toLowerCase().trim().replace(" ", "_") + "_" + (new Date()).getTime(),
            track: document.getElementsByName('track')[0].value.toLowerCase().trim(),
            fetchInterval: document.getElementsByName('fetchInterval')[0].value,
            fetchFrom: document.getElementsByName('fetchFrom')[0].value,
            langFilters: langFilters.join(","),
            subscribedProfiles: document.getElementsByName('subscribedProfiles')[0].value,
            provider: document.getElementsByName('provider')[0].value,
    };
    
    if(eventType != null && eventType.toLowerCase() === "snopes" && eventTypeId != null){
        data.globalEventDefinition = {};
        data.globalEventDefinition.id = eventTypeId;
    }else if(eventType != null && eventType.toLowerCase() === "gdelt" && eventTypeId != null){
        data.glideMaster = {};
        data.glideMaster.id = eventTypeId;
    }
    
    var runAfterCreate = false;
    if($('#runAfterCreate').is(':checked')){
        runAfterCreate = true;
    }
    
    var url = "${rc.getContextPath()}/collection/create?runAfterCreate="+runAfterCreate;
    
    $.ajax({
        type: "POST",
        url: url,
        dataType:"json",
        data: JSON.stringify(data),
        
        success: function(data){
            if(data.success){
                showInfoAlert(data.message);
                location.href = "${rc.getContextPath()}/collection/view/details?id="+data.data.id;
            }else{
                showErrorAlert(data.message);
            }
        }
    });
}