// FORMATTING

// formatting date and time.
function formatDateTime(toConvert){
	if (toConvert == null) { return null; }
    let datetimeArray = toConvert.split(" ");
    let date = moment(datetimeArray[0] + " " + datetimeArray[1]);
    return date.format('l LT');
}

// formatting for money.
function formatMoney(toConvert){
	return numeral(toConvert).format('$0,0.00');
}

// formula for images
function formatImage(theImage){
	if (theImage == null ) { return null; }
	return '<img src="data:image/jpeg;base64,'+theImage+'" />';
}