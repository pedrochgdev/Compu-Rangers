<%@ Page Title="Error Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Error.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Error" %>

<asp:Content ID="ErrorPayment" ContentPlaceHolderID="MainContent" runat="server">
     <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
      <div class="text-center p-4 border rounded shadow-sm bg-light">
        <svg width="80" height="80" fill="#dc3545" class="mb-3" viewBox="0 0 16 16">
          <path d="M8.982 1.566a1.13 1.13 0 0 0-1.964 0L.165 13.233C-.292 14.011.257 15 1.147 15h13.706c.89 0 1.438-.989.982-1.767L8.982 1.566zM7.002 4.999h2v4h-2v-4zm0 5h2v2h-2v-2z"/>
        </svg>
        <h2 class="text-danger mb-2">Error en el Pago</h2>
        <p class="text-muted">Ocurrió un problema al procesar tu pago. Intenta más tarde.</p>
      </div>
    </div>

</asp:Content>

