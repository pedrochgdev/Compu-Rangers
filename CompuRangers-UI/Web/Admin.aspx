<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="Admin.aspx.cs" Inherits="Web.Admin" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">

    <main>
        <body>
                <div class="container mt-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h2>Inventario de Productos</h2>
                        <a href="#" class="btn btn-primary">Añadir inventario</a>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped table-bordered align-middle">
                            <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Fecha de Creación</th>
                                <th>Precio de Compra</th>
                                <th>Marca</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>001</td>
                                <td>Teclado Mecánico RGB</td>
                                <td>2025-04-12</td>
                                <td>$49.99</td>
                                <td>Logitech</td>
                            </tr>
                            <tr>
                                <td>002</td>
                                <td>Mouse Inalámbrico</td>
                                <td>2025-04-15</td>
                                <td>$25.00</td>
                                <td>Razer</td>
                            </tr>
                            <tr>
                                <td>003</td>
                                <td>Monitor 27" 144Hz</td>
                                <td>2025-04-20</td>
                                <td>$199.00</td>
                                <td>Samsung</td>
                            </tr>
                            <tr>
                                <td>004</td>
                                <td>Auriculares Gaming</td>
                                <td>2025-04-21</td>
                                <td>$69.90</td>
                                <td>HyperX</td>
                            </tr>
                            <tr>
                                <td>005</td>
                                <td>Webcam Full HD</td>
                                <td>2025-04-25</td>
                                <td>$39.50</td>
                                <td>Logitech</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
          </body>
    </main>
</asp:Content>
