<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="404.aspx.cs" Inherits="Web._404" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>404 - Página no encontrada</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    
    <style>
        body {
            background-color: #111;
            color: white;
            font-family: 'Segoe UI', sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .logo span:first-child {
            color: white;
        }
        .logo span:last-child {
            color: #EB484C;
        }
        .error-code {
            font-size: 6rem;
            font-weight: bold;
            color: #EB484C;
        }
        .error-text {
            font-size: 1.5rem;
        }
        .btn-home {
            background-color: #EB484C;
            border: none;
        }
        .btn-home:hover {
            background-color: #c03b3f;
        }
        .content {
            flex-grow: 1;
        }
    </style>
</head>
<body>
    <header class="bg-dark py-3 px-4">
        <div class="container d-flex align-items-center">
            <a class="navbar-brand d-flex align-items-center logo text-decoration-none" href="~/Catalogo/Home.aspx" runat="server">
                <span class="fw-bold" style="font-size: calc(1.5rem + 0.3vw);">COMPU</span>
                <span class="fw-bold" style="font-size: calc(1.5rem + 0.3vw);">RANGERS</span>
            </a>
        </div>
    </header>

    <main class="container text-center my-auto content d-flex flex-column justify-content-center align-items-center py-5">
        <div class="error-code">404</div>
        <p class="error-text mb-4">Lo sentimos, la página que buscas no existe.</p>
        <a href="Catalogo/Home.aspx" class="btn btn-home px-4 py-2 text-white">Volver al inicio</a>
    </main>

    <footer class="text-center text-muted py-3">
        &copy; <%= DateTime.Now.Year %> COMPU<span style="color:#EB484C;">RANGERS</span>. Todos los derechos reservados.
    </footer>
</body>
</html>
