$(document).ready(function() {
    loadCars();
})

function loadCars() {
    clearCars(); 
    const carRows = $('#carContentRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/guildcars.com/admin/vehicles',
        success: function (carArray) {
            $.each(carArray, function (index, car) {
              const tag = car.year + ' ' + car.model.make.name + ' ' + car.model.name;
      
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
                '.jpg" width="90" height="70" alt="car">' +
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
                '<a href="editCar" th:href="@{/guildcars.com/editCar(id=' +
                carId +
                ')}">Edit</a><td>';
              row += '</tr>';
      
              carRows.append(row);
            });
        },
        error: function () {
        },
    });
}
      
function clearCars() {
$('#carContentRows').empty();
}
