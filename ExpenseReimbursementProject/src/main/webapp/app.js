window.onload = function(){
	loadNavView();
	loadJumbotronView();
	
}

// VIEWS
// Load NavigationBar.
function loadNavView(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#nav').html(xhr.responseText);

			$('#nav-home').on('click', loadJumbotronView);
			$('#nav-profile').on('click', loadUserInfoView);
			$('#nav-pending-memories').on('click', loadPendingView);
			$('#nav-resolved-memories').on('click', loadResolvedView);
			$('#nav-all-memories').on('click', loadAllView);
			$('#nav-create-memory').on('click', loadSubmissionView);
		}
	}
	xhr.open("GET", "ajaxNavView", true);
	xhr.send();
}

// Load Jumbotron
function loadJumbotronView(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#view').html(xhr.responseText);
			retrievePersonalInformation();
			$('.navbar-nav li.active').removeClass('active');
			$('#nav-home').addClass('active');
		}
	}
	xhr.open("GET", "ajaxJumbotronView", true);
	xhr.send();
}

// Load User Information View
function loadUserInfoView(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#view').html(xhr.responseText);
			retrievePersonalInformation();
			$('.navbar-nav li.active').removeClass('active');
			$('#nav-profile').addClass('active');
		}
	}
	xhr.open("GET", "ajaxInfoView", true);
	xhr.send();
}

// Load Pending View
function loadPendingView(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#view').html(xhr.responseText);
			retrievePendingMemories();
			$('.navbar-nav li.active').removeClass('active');
			$('#nav-memories').addClass('active');
		}
	}
	xhr.open("GET", "ajaxPendingView", true);
	xhr.send();	
}

// Load Resolved View
function loadResolvedView(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#view').html(xhr.responseText);
			retrieveResolvedMemories();

			$('.navbar-nav li.active').removeClass('active');
			$('#nav-memories').addClass('active');
		}
	}
	xhr.open("GET", "ajaxResolvedView", true);
	xhr.send();	
}

// Load All View
function loadAllView(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#view').html(xhr.responseText);
			retrieveAllMemories();

			$('.navbar-nav li.active').removeClass('active');
			$('#nav-memories').addClass('active');
		}
	}
	xhr.open("GET", "ajaxAllView", true);
	xhr.send();	
}

// Load Memory Submission
function loadSubmissionView() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			$('#view').html(xhr.responseText);

			$('.navbar-nav li.active').removeClass('active');
			$('#nav-memories').addClass('active');
		}
	}
	xhr.open("GET", "ajaxSubmissionView", true);
	xhr.send();	
}

// DATA RETRIVAL
// Get information of user. ALL SITE MUST HAVE ACCESS TO THIS.
function retrievePersonalInformation(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			
			let userInfo = JSON.parse(xhr.responseText);
			$('.i-firstname').append(userInfo.firstName);
			$('.i-lastname').append(userInfo.lastName);
			$('.i-email').append(userInfo.email);
			$('.i-username').append(userInfo.username)
		}
	}
	xhr.open("POST", "ajaxUserInfo", true);
	xhr.send();
}


// Administration Views
function loadListEmployeeView() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			
			$('#view').html(xhr.responseText);

			retrieveAllEmployees();
			
			$('.navbar-nav li.active').removeClass('active');
			$('#nav-employees').addClass('active');
		}
	}
	xhr.open("GET", "ajaxListEmployeeView", true);
	xhr.send();
}
