(() => {
'use strict';

const target = document.getElementById('target');
const input = document.getElementById('in');

if(!!localStorage.getItem('les6')) {
	target.innerHTML = localStorage.getItem('les6');
	input.value = localStorage.getItem('les6');
}

input.addEventListener('keyup', e => {
	localStorage.setItem('les6', e.target.value)
	target.innerHTML = e.target.value;
});

window.addEventListener('storage', e => {
	if(e.key !== 'les6') return;
	target.innerHTML = e.newValue;
	input.value = e.newValue;
});
})();