/**
 * Created by LENOVO on 2019/8/15.
 */
(function(window, $) {
    $(window).resize(resizeWindow);
    function resizeWindow(){
        var whdef = 100/1920;
        var wH = window.innerHeight;
        var wW = window.innerWidth;
        var rem = wW * whdef;
        $('html').css('font-size', rem + "px");
    }
    resizeWindow();
})(window,$);
