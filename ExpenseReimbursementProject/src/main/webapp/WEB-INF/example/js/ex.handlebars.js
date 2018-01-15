window.onload = function () {
	  var source   = $("#some-template").html();
  var template = Handlebars.compile(source);
  var data = { users: [
      {username: "alan", firstName: "Alan", lastName: "Johnson", email: "alan@test.com" },
      {username: "allison", firstName: "Allison", lastName: "House", email: "allison@test.com" },
      {username: "ryan", firstName: "Ryan", lastName: "Carson", email: "ryan@test.com" }
    ]};
  $("#content-placeholder").html(template(data));
}