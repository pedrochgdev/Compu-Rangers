<%@ Page Title="Success Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Success.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Success" %>

<asp:Content ID="SuccessPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
      <div class="text-center p-4 border rounded shadow-sm bg-light">
        <svg width="80" height="80" fill="green" class="mb-3" viewBox="0 0 16 16">
          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM7 11.5l5-5-1.5-1.5L7 8.5 5.5 7 4 8.5l3 3z"/>
        </svg>
        <h2 class="text-success mb-2">¡Pago Completado!</h2>
        <p class="text-muted">Gracias por tu compra. Puedes cerrar esta ventana.</p>
      </div>
    </div>

</asp:Content>

