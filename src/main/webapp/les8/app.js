(() => {
"use strict";

let countries = [];
let filter = '';

const loadCountries = () => {
	const xhr = new XMLHttpRequest();
	xhr.open('GET', '../restservices/countries', true);
	xhr.send();

	xhr.onreadystatechange = e => {
		if(e.target.readyState !== 4) return;
		countries = JSON.parse(e.target.response);
		rerender();
	};
}

const rerender = () => {
	document.getElementById('countryTable').innerHTML = `<tr>
		<th>Land</th>
		<th>Hoofdstad</th>
		<th>Regio</th>
		<th>Acties</th>
	</tr>`;
	const filtered = countries.filter(country => {
		for (const key in country) {
			if(!country.hasOwnProperty(key)) continue;
			if(typeof country[key] === "string" && country[key].toLowerCase().indexOf(filter.toLowerCase()) >= 0) return true;
		}
		return false;
	});
	for(const country in filtered) document.getElementById('countryTable').innerHTML +=
		`<tr class="item ${filtered[country]['iso']}">
			<td>${filtered[country]['name']}</td>
			<td>${filtered[country]['capital']}</td>
			<td>${filtered[country]['region']}</td>
			<td><a href="#" class="editBtn">Aanpassen</a> <a href="#" class="rmBtn">Verwijderen</a></td>
		</tr>`;
	const editItems = document.getElementsByClassName('editBtn');
	for(let i = 1; i < editItems.length; i++) editItems[i].addEventListener('click', showEdit);
	const removeItems = document.getElementsByClassName('rmBtn');
	for(let i = 1; i < removeItems.length; i++) removeItems[i].addEventListener('click', removeItem);
}

const removeItem = e => {
	e.preventDefault();
	const iso = e.target.parentNode.parentNode.className.split(' ')[1];
	const xhr = new XMLHttpRequest();
	xhr.open('DELETE', `../restservices/countries/${iso}`, true);
	xhr.send();
	for(let i = 0; i < countries.length; i++) if(countries[i]['iso'] === iso) countries.splice(i, 1);
	rerender();
}

const showEdit = e => {
	
}

document.getElementById('filterInput').addEventListener('keyup', e => {
	filter = e.target.value;
	rerender();
});

loadCountries();
})();