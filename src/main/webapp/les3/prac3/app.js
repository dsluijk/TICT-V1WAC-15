(() => {
"use strict";

const display = document.getElementById('display');
const buttons = document.getElementsByTagName('button');
let latestResult = 0;
let type = '';

const clickEvent = (e) => {
	const action = e.target.id.split('_')[1];
	switch(action) {
	case 'clear':
		latestResult = 0;
		display.innerHTML = 0;
		break;
	case 'eq':
		if(type === '') return;
		latestResult = eval(parseFloat(latestResult) + type + parseFloat(display.innerHTML));
		type = '';
		display.innerHTML = latestResult;
		break;
	case 'plus':
		type = '+';
		if(latestResult === 0) latestResult = parseFloat(latestResult) + parseFloat(display.innerHTML);
		display.innerHTML = 0;
		break;
	case 'min':
		type = '-';
		if(latestResult === 0) latestResult = parseFloat(latestResult) - parseFloat(display.innerHTML);
		display.innerHTML = 0;
		break;
	case 'prod':
		type = '*';
		if(latestResult === 0) latestResult = parseFloat(latestResult) * parseFloat(display.innerHTML);
		display.innerHTML = 0;
		break;
	case 'div':
		type = '/';
		if(latestResult === 0) latestResult = parseFloat(latestResult) / parseFloat(display.innerHTML);
		display.innerHTML = 0;
		break;
	default:
		display.innerHTML = parseFloat((parseFloat(display.innerHTML) || 0) + action);
		break;
	}
}

for(const button in buttons) {
	if(!buttons.hasOwnProperty(button)) continue;
	buttons[button].addEventListener('click', clickEvent);
}
})();