(function($) {
    $.LinkQTip = function(element, options) {
        var defaults = {
            thumbnailWidth: 320,
            thumbnailHeight: 240,
            viewPortWidth: 1024,
            offsetTop: 0,
            offsetLeft: 20,
            onLinkQTipShow: function() {}
        };

        var qtip = this;
        var div = null;    
        qtip.settings = {};

        qtip.init = function() {
            qtip.settings = $.extend({}, defaults, options);
            $(element).hover(showQTip, hideQTip);        
        };

        qtip.toggle = function() {
            if(div != null) {
                hideQTip();
            } else {
                showQTip();
            }
        };
        
        var showQTip = function() {
            if(div != null) {
                return;
            }
            var o = qtip.settings;
            var topPosition = $(element).offset().top + o.offsetTop;
            var leftPosition = $(element).offset().left + $(element).width() + o.offsetLeft;
            div = $('<div/>');
            $(div).css({
                left: leftPosition+'px',
                top: topPosition+'px',
                width: o.thumbnailWidth+'px',
                height: o.thumbnailHeight+'px'
            });
            $(div).addClass('link-qtip');            
            var imgUrl = 'http://api.webthumbnail.org?width='+o.thumbnailWidth+'&height='+o.thumbnailHeight+'&format=png&screen='+o.viewPortWidth+'&url='+$(element).attr("href");
            var img = $('<img />');
            img.attr('src', imgUrl);
            
            $(div).append(img);
            $('body').append(div);    
            o.onLinkQTipShow();    
        };
        
        var hideQTip = function() {
            $(div).remove();
            div = null;
        };
       
        qtip.init();
    };
    $.fn.linkQTip = function (options) {
        return this.each(function () {
            if (undefined == $(this).data('LinkQTip')) {
                var qtip = new $.LinkQTip(this, options);
                $(this).data('LinkQTip', qtip);
            }
        });
    };
})(jQuery);
