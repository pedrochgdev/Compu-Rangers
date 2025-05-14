<%@ Page Title="VerMas" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="VerMas.aspx.cs" Inherits="Web.VerMas" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <main aria-labelledby="title">
        <div class="product-container row">
            <!-- Imagen -->
            <div class="col-md-6">
              <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" alt="Intel Core i7 12700K" class="product-image">
            </div>

            <!-- Detalles -->
            <div class="col-md-6">
              <h2>Intel Core i7 12700K</h2>
              <div class="mb-2">
                <span class="badge-category">Procesadores</span>
                <span class="badge-category">Intel 12va Gen</span>
              </div>
              <p>
                El Intel Core i7 12700K ofrece un rendimiento sobresaliente gracias a sus 12 núcleos híbridos y 20 hilos.
                Perfecto para gamers, creadores de contenido y usuarios exigentes.
              </p>

              <!-- Precio y selección de cantidad -->
            <div class="d-flex align-items-center">
                <p class="price me-3">$389.99</p>
                <div class="input-group">
                  <button class="btn btn-outline-secondary" type="button" onclick="decreaseQuantity()">-</button>
                  <input type="text" id="quantity" class="form-control text-center" value="1">
                  <button class="btn btn-outline-secondary" type="button" onclick="increaseQuantity()">+</button>
                </div>
              </div>

              <button class="btn btn-buy mt-3">Añadir al carrito</button>
            </div>
          </div>
    </main>
</asp:Content>