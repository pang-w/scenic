jQuery(function ($) {
    loadUrlParam();
    $("#btnSearch").click(function () {
        search("1");
    });
});
function search(page) {

    support.ajax("admin/pages/buildIndex", null, function (data) {

    });
}

function loadUrlParam() {
    var param = urlParam();
    var flag = false;
    if (param.cno) {
        $("#cno").val(param.cno);
        flag = true;
    }
    if (param.isuNum) {
        $("#isuNum").val(param.isuNum);
        flag = true;
    }
    if(flag) {
        search("1");
    }
}

function goColleciton(cno) {
    window.location = "collection.html?cno=" + cno;
}
function goSolutionIns(isuNum) {
        window.location = "solutionIns.html?isuNum=" + isuNum;
}