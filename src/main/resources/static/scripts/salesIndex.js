$(document).ready(function () {
  alert('page is loaded');
  $('#searchContainer').hide();
  loadCars();
  //addContact();
  //updateContact();
});

// load function to GET data
function loadCars() {
  //clearContactTable(); // clear the contacts table
  $('#searchContainer').show();
  const carRows = $('#carRows');

  $.ajax({
    type: 'GET',
    headers: { 'Access-Control-Allow-Origin:': '*' },
    crossDomain: true,
    dataType: 'jsonp',
    url: 'http://localhost:8080/guildcars.com/admin/vehicles',
    success: function (carArray) {
      $.each(carArray, function (index, car) {
        const name = car.year + ' ' + car.model.make.name + ' ' + car.model;

        const picture = car.picture;
        console.log(picture);
        const bodyStyle = car.bodyStyle;
        const trans = car.transmission;
        const color = car.color;
        const interior = car.interior;
        const mileage = car.mileage;
        const vin = car.vin;
        const salesPrice = car.salesPrice;
        const msrp = car.msrp;

        const carId = car.id;

        let row = '<tr>';
        row += '<td>' + name + '<br>' + picture + '</td>';

        row += '<td>' + bodyStyle + '<br>' + trans + '<br>' + color + '</td>';

        row += '<td>' + interior + '<br>' + mileage + '<br>' + vin + '</td>';
        row +=
          '<td>' +
          salesPrice +
          '<br>' +
          msrp +
          '<br>' +
          '<button type="button" class="btn btn-danger" onclick="purchase(' +
          carId +
          ')">Purchase</button></td>';
        row += '</tr>';

        carRows.append(row);
      });
    },
    error: function () {
      // $('#errorMessages').append(
      //   $('<li>')
      //     .attr({ class: 'list-group-item list-group-item-danger' })
      //     .text('Error calling web service. Please try again later.')
      // );
    },
  });
}
