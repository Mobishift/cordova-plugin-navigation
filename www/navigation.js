window.navigationService = {
	execute: function(action, successCallback, errorCallback, params) {
		cordova.exec(    
			successCallback, 
			errorCallback,
			"Navigation",
			action,
			params
		)
	},
	navigate: function(lng, lat, successCallback, errorCallback) {
		this.execute("navigate", successCallback, errorCallback, [lng, lat]);
	}
}
module.exports = navigationService;