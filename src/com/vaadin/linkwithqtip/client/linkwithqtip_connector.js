com_vaadin_linkwithqtip_LinkWithQTip = function() {
	var aElement = $('<a/>', {
		text: this.getState().text,
		href: this.getState().url
	});
	
	var self = this;
	var linkQTip = aElement.linkQTip({
		onLinkQTipShow: self.onLinkQTipOpened
	});
	$(this.getElement()).append(aElement);
	
	this.toggle = function() {
		aElement.data('LinkQTip').toggle();
	};
	
    this.onStateChange = function () {
    	aElement.html(this.getState().text);
    };	
};