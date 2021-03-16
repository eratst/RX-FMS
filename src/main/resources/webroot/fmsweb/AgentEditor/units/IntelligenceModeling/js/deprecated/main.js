var $ = window.jQuery

var $window = $(window)
var sectionTop = $('.top').outerHeight() + 20
var $createDestroy = $('#switch-create-destroy')

function capitalize (string) {
  return string.charAt(0).toUpperCase() + string.slice(1)
}

window.hljs.initHighlightingOnLoad()
$(function () {
 
  $('#switch-state').bootstrapSwitch()

})
