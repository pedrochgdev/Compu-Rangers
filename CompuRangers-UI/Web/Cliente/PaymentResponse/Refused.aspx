<%@ Page Title="Refused Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Refused.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Refused" %>

<asp:Content ID="RefusedPayment" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
      <div class="text-center p-4 border rounded shadow-sm bg-light">
        <svg width="80" height="80" fill="#dc3545" class="mb-3" viewBox="0 0 16 16">
          <path d="M8.982 1.566a1.13 1.13 0 0 0-1.964 0L.165 13.233c-.457.778.091 1.767.982 1.767h13.706c.89 0 1.438-.99.982-1.767L8.982 1.566zm-2.02 4.905a.5.5 0 0 1 .707 0L8 8.293l.33-.33a.5.5 0 0 1 .707.707L8.707 9l.33.33a.5.5 0 0 1-.707.707L8 9.707l-.33.33a.5.5 0 0 1-.707-.707L7.293 9l-.33-.33a.5.5 0 0 1 0-.707z"/>
        </svg>
        <h2 class="text-danger mb-2">Pago Rechazado</h2>
        <p class="text-muted">La transacción fue rechazada. Intenta con otro método.</p>
      </div>
    </div>


</asp:Content>


