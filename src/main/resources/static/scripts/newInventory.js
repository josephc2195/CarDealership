$(document).ready(function () {
  //alert('page is loaded');

  loadCars();
  //addContact();
  //updateContact();
});

// load function to GET data
function loadCars() {
  clearCarTable(); // clear car table
  const carRows = $('#carContentRows');

  $.ajax({
    type: 'GET',
    // headers: { 'Access-Control-Allow-Origin:': '*' },
    // crossDomain: true,
    // dataType: 'jsonp',
    url: 'http://localhost:8080/guildcars.com/admin/vehicles',
    success: function (carArray) {
      $.each(carArray, function (index, car) {
        const tag = car.year + ' ' + car.model.make.name + ' ' + car.model.name;
        alert("test");
        const picture = car.picture;
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
        row +=
          '<td>' +
          '<img src="/images/' +
          picture +
          '.jpg" width="50" height="50" alt="car">' +
          '</td>';
        row += '<td>' + tag + '</td>';
        row += '<td>' + bodyStyle + '</td>';
        row += '<td>' + trans + '</td>';
        row += '<td>' + color + '</td>';
        row += '<td>' + interior + '</td>';
        row += '<td>' + mileage + '</td>';
        row += '<td>' + vin + '</td>';
        row += '<td>' + salesPrice + '</td>';
        row += '<td>' + msrp + '</td>';
        row +=
          '<td>' +
          '<button type="button" class="btn btn-danger" onclick="purchase(' +
          carId +
          ')">Purchase</button> <td>';
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
    }
  });
}

//clear table
function clearCarTable() {
  $('#carContentRows').empty();
}
