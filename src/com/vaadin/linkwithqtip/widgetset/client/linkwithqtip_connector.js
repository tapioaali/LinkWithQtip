com_vaadin_linkwithqtip_LinkWithQTip = function() {
	var aElement = $('<a>', {
		text: this.getState().text,
		href: this.getState().url
	});
	
	var rpcProxy = this.getRpcProxy();
	var linkQTip = $(aElement).linkQTip({
		onLinkQTipShow: function() {
			console.log(rpcProxy);
			rpcProxy.onLinkQTipOpened();
		}
	});
	$(this.getElement()).append(aElement);
	
    this.registerRpc({
        toggle: function() {
        	$(aElement).data('LinkQTip').toggle();  
        }
    });
	
    this.onStateChange = function () {
    	$(aElement).attr("href", this.getState().url);
    	$(aElement).html(this.getState().text);
    };	
};