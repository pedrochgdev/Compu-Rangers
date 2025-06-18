<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="MiPerfil.aspx.cs" Inherits="Web.Usuario.MiPerfil" %>

<asp:Content ID="PerfilContent" ContentPlaceHolderID="MainContent" runat="server">
    <main>
        <div class="container mt-5">
    <div class="row g-4">
      <!-- Menú lateral -->
      <div class="col-md-3">
        <div class="list-group sidebar">
          <a href="#perfil" class="list-group-item list-group-item-action">Usuario</a>
          <a href="#historial" class="list-group-item list-group-item-action">Historial de compras</a>
          <a href="#direcciones" class="list-group-item list-group-item-action">Direcciones guardadas</a>
          <a href="#metodos-pago" class="list-group-item list-group-item-action">Métodos de pago</a>
          <a href="#preferencias" class="list-group-item list-group-item-action">Preferencias</a>
          <a href="#ayuda" class="list-group-item list-group-item-action">Ayuda</a>
          <a href="#terminos" class="list-group-item list-group-item-action">Términos y Condiciones</a>
        </div>
      </div>

      <!-- Secciones -->
      <div class="col-md-9">
        <!-- Perfil de usuario -->
        <section id="perfil" class="mb-5">
          <div class="card shadow">
            <div class="card-header">
              <h5 class="mb-0">Perfil de Usuario</h5>
            </div>
            <div class="card-body">
              <form id="perfilForm">
                <div class="mb-2">
                  <label for="nombre" class="form-label">Nombre completo</label>
                  <input type="text" id="nombre" class="form-control" value="Luis Carhuayo" readonly>
                </div>
                <div class="mb-2">
                  <label for="dni" class="form-label">DNI</label>
                  <input type="text" id="dni" class="form-control" value="12345678" readonly>
                </div>
                <div class="mb-2">
                  <label for="correo" class="form-label">Correo electrónico</label>
                  <input type="email" id="correo" class="form-control" value="luis@example.com" readonly>
                </div>
                <div class="mb-2">
                  <label for="telefono" class="form-label">Teléfono</label>
                  <input type="text" id="telefono" class="form-control" value="987654321" readonly>
                </div>
                <div class="mb-2">
                  <label for="fecha-nacimiento" class="form-label">Fecha de nacimiento</label>
                  <input type="date" id="fecha-nacimiento" class="form-control" value="2002-01-01" readonly>
                </div>
                <div class="mb-3">
                  <label for="direccion" class="form-label">Dirección</label>
                  <input type="text" id="direccion" class="form-control" value="Av. Los Próceres 123" readonly>
                </div>
                <div class="d-flex justify-content-between">
                  <button type="button" class="btn btn-primary btn-sm" onclick="activarEdicion()">Editar</button>
                  <button type="submit" class="btn btn-success btn-sm d-none" id="guardarBtn">Guardar</button>
                </div>
              </form>
            </div>
          </div>
        </section>

        <!-- Historial de compras -->
        <section id="historial" class="mb-5">
          <div class="card shadow">
            <div class="card-header">
              <h5 class="mb-0">Historial de compras</h5>
            </div>
            <div class="card-body">
              <ul class="list-group">
                <li class="list-group-item">
                  <strong>Orden #100234</strong> - 10/06/2025<br>
                  5 × Memorias RAM 16GB | Total: S/ 850.00
                </li>
                <li class="list-group-item">
                  <strong>Orden #100221</strong> - 03/06/2025<br>
                  10 × SSD 512GB | Total: S/ 1250.00
                </li>
                <li class="list-group-item">
                  <strong>Orden #100208</strong> - 25/05/2025<br>
                  3 × Tarjetas Gráficas RTX 4060 | Total: S/ 3900.00
                </li>
              </ul>
            </div>
          </div>
        </section>

        <!-- Direcciones guardadas -->
        <section id="direcciones" class="mb-5">
          <div class="card shadow">
            <div class="card-header">
              <h5 class="mb-0">Direcciones guardadas</h5>
            </div>
            <div class="card-body">
              <p>Dirección principal: Av. Los Próceres 123, Lima, Perú</p>
              <button class="btn btn-outline-primary btn-sm">Editar direcciones</button>
            </div>
          </div>
        </section>

        <!-- Métodos de pago -->
        <section id="metodos-pago" class="mb-5">
          <div class="card shadow">
            <div class="card-header">
              <h5 class="mb-0">Métodos de pago</h5>
            </div>
            <div class="card-body">
              <p>Tarjeta principal: Visa **** 4321</p>
              <button class="btn btn-outline-primary btn-sm">Gestionar tarjetas</button>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>

    </main>
</asp:Content>

