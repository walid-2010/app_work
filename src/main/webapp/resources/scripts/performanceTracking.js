/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sendPerformance(uuid) {

    var appContext = '';
    var url;
//    var uuid = "#{baseBackBean.requestData.uuid}";
//    alert(uuid);
    if (window.location.pathname.lastIndexOf("/") !== 0) {
        appContext = window.location.pathname.split('/')[1];
        url = window.location.origin + '/' + appContext + '/webresources/log';
    } else {
        url = window.location.origin + '/webresources/log';
    }

    var per = JSON.stringify(window.performance);
    var ent = JSON.stringify(window.performance.getEntries());
    var pagePathname = window.location.pathname;
    var loadTime = (new Date()).getTime();
    $.ajax({
        type: "POST",
        data: '{'
                + '"performance": ' + per
                + ', '
                + '"pagePath": "' + pagePathname + '"'
                + ', '
                + '"entries": ' + ent
                + ', '
                + '"LoadTime": ' + loadTime
                + ', '
                + '"UUID": "' + uuid + '"'
                + '}',
        url: url,
        contentType: "application/json"
    });
}

