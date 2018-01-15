Handlebars.registerHelper('table-list', function(items, options) {
	var out = "<table class=\"table table-hover\">";

	for(var i=0, l=items.length; i<l; i++) {
		out = out + "<tr>" + options.fn(items[i]) + "</tr>";
	}

	return out + "</table>";
});