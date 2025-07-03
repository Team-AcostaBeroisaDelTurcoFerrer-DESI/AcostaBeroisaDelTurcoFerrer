// script.js




const boton = document.getElementById('agregarProducto');
const precioTotalSpan = document.getElementById('precioTotal');
let precioTotal = 0;
const iva = 0.21; // 21% de IVA

boton.addEventListener('click', () => {
    // Aquí iría la lógica para obtener el precio del producto
    // Supongamos que el precio del producto es 100
    const precioProducto = 100;

    precioTotal += precioProducto;
    const precioConIva = precioTotal * (1 + iva);
    precioTotalSpan.textContent = precioConIva.toFixed(2);
});

// Función para agregar un producto al carrito
function agregarAlCarrito(producto) {
    // Obtener el carrito actual del localStorage o sessionStorage
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
  
    // Agregar el producto al carrito
    carrito.push(producto);
  
    // Almacenar el carrito actualizado
    localStorage.setItem('carrito', JSON.stringify(carrito));
  
    // Actualizar la interfaz de usuario (mostrar el carrito)
    mostrarCarrito();
  }
  
  // Función para mostrar el carrito
  function mostrarCarrito() {
    const carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    const carritoElement = document.getElementById('carrito');
    carritoElement.innerHTML = ''; // Limpiar el carrito antes de mostrar los productos
  
    carrito.forEach(producto => {
      const li = document.createElement('li');
      li.textContent = `${producto.nombre} - $${producto.precio}`;
      carritoElement.appendChild(li);
    });
  }

  function limpiarCarrito() {
    localStorage.removeItem('carrito');
    // Actualiza la interfaz de usuario para mostrar que el carrito está vacío
    mostrarCarrito();
}
function calcularTotalCarrito() {
    // Obtener todos los productos del localStorage (suponiendo que están almacenados con un prefijo "producto_")
   // let productos = Object.keys(localStorage).filter(key => key.startsWith('producto'));
    let productos = [];

  // Obtener todas las claves del localStorage
  let claves = Object.keys(localStorage);

  // Iterar sobre cada clave y agregar el producto al array
  claves.forEach(clave => {
    let producto = JSON.parse(localStorage.getItem(clave));
    productos.push(producto);
  });

    let total = 0;

    // Iterar sobre los productos y sumar los precios
    productos.forEach(producto => {
        let precio = parseFloat(localStorage.getItem(producto));
        if (!isNaN(precio)) {
            total += precio;
        }
    });

    // Mostrar el total en un elemento HTML con el id "totalCarrito"
    document.getElementById('totalCarrito').textContent = `Total: $${total.toFixed(2)}`;
}


function enviarCorreo() {

  emailjs.init('Pf93voxEOD7J5kZawSwGm'); // Reemplaza con tu User ID

  // Obtener los valores de los campos del formulario
  const apellido = document.getElementById('apellido').value;
  const nombre = document.getElementById('nombre').value;
  const email = document.getElementById('email').value;
  const comentario = document.getElementById('comentario').value;

  // Crear un objeto con los datos del formulario
  const templateParams = {
    from_apellido: apellido,
    from_nombre: nombre,
    reply_to: email,
    comentario: comentario,
  };

  // Enviar el correo electrónico
  emailjs.send('service_m11d11a', 'template_nr0xg0c', templateParams)
    .then(function(response) {
      console.log('SUCCESS!', response.status, response.text);
      alert('¡Mensaje enviado correctamente!');
      // Aquí puedes agregar acciones adicionales después de enviar el correo,
      // como limpiar los campos del formulario o mostrar un mensaje de agradecimiento más elaborado
    })
    .catch(function(error) {
      console.error('FAILED...', error);
      alert('Lo sentimos, ocurrió un error al enviar el mensaje. Por favor, inténtalo de nuevo más tarde.');
      // Aquí puedes manejar los errores de forma más específica,
      // como mostrar un mensaje de error más detallado al usuario
    });
}