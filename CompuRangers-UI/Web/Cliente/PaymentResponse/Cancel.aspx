<%@ Page Title="Cancel Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Cancel.aspx.cs" Inherits="Web.Cliente.PaymentResponse.Cancel" %>

<asp:Content ID="CancelPayment" ContentPlaceHolderID="MainContent" runat="server">
   <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
      <div class="text-center p-4 border rounded shadow-sm bg-light">
        <svg width="80" height="80" fill="#ffc107" class="mb-3" viewBox="0 0 16 16">
          <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zM4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
        </svg>
        <h2 class="text-warning mb-2">Pago Cancelado</h2>
        <p class="text-muted">Cancelaste el proceso. Si fue un error, puedes intentarlo de nuevo.</p>
      </div>
    </div>


</asp:Content>
