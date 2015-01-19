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
	navigate: function(flat, flng, fname, tlat, tlng, tname, successCallback, errorCallback) {
        this.execute("navigate", successCallback, errorCallback, [flat, flng, fname, tlat, tlng, tname]);
	}
}
module.exports = navigationService;