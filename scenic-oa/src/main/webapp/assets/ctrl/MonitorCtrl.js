jQuery(function ($) {
    support.ajax("monitorMsg", {}, function (data) {
            var monitorMsg = data.respInfo.monitorMsg;
            $('#brokerMsg').html($('#brokerMsgInfo').render(monitorMsg.brokerMsg));
            $('#queueMsg').html($('#queueMsgList').render(monitorMsg.queueMsgs));
            $('#topicMsg').html($('#topicMsgList').render(monitorMsg.topicMsgs));
        }
    );
})