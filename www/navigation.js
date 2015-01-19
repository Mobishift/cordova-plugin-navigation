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
	navigate: function(lat, lng, name, successCallback, errorCallback) {
		this.execute("navigate", successCallback, errorCallback, [lat, lng, name]);
	}
}
module.exports = navigationService;