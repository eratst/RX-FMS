'use strict';

angular.module('myApp.version', [
		'myApp.version.interpolate-filter',
		'myApp.version.version-directive'
	])
	.value('version', '4.3.2');
