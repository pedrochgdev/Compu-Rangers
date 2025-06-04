<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Web._Default" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">
    <script src="Scripts/CompuRangers/Login.js"></script>

    <main>
        <form method="GET" >
            <div class="row g-3 mb-4">
                <div class="col-md-4">
                    <input type="text" name="nombre" class="form-control" placeholder="Buscar por nombre">
                </div>
                <div class="col-md-3">
                    <select name="categoria" class="form-select">
                        <option value="">Todas las categorías</option>
                        <option value="Procesadores">Procesadores</option>
                        <option value="Placas Madre">Placas Madre</option>
                        <option value="Memorias RAM">Memorias RAM</option>
                        <option value="Almacenamiento">Almacenamiento</option>
                        <option value="Periféricos">Periféricos</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="number" name="precio" class="form-control" placeholder="Precio máximo">
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary w-100">Filtrar</button>
                </div>
            </div>

        </form>

        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <div class="col">
                <div class="card h-100">
                    <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="Intel Core i7 12700K">
                    <div class="card-body">
                        <h5 class="card-title">Intel Core i7 12700K</h5>
                        <p class="card-text">Procesador de 12 núcleos para alto rendimiento.</p>
                        <div class="d-flex gap-2">
                            <span class="badge bg-secondary">Procesadores</span>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between align-items-center">
                        <strong class="text-primary">$389.99</strong>
                        <div class="d-flex flex-row justify-content-end align-item-center gap-2">
                            <a class="btn btn-sm btn-warning" runat="server" href="~/VerMas">Ver más</a>
                            <asp:LinkButton ID="lbAgregarLOV" runat="server" CssClass="btn btn-sm btn-success" Text="Añadir al carrito" OnClick="btnLogin" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100">
                    <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="ASUS ROG STRIX Z790">
                    <div class="card-body">
                        <h5 class="card-title">ASUS ROG STRIX Z790</h5>
                        <p class="card-text">Placa madre de alto desempeño con soporte DDR5.</p>
                        <div class="d-flex gap-2">
                            <span class="badge bg-secondary">Placas Madre</span>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between align-items-center">
                        <strong class="text-primary">$289.50</strong>
                        <div class="d-flex flex-row justify-content-end align-item-center gap-2">
                            <a class="btn btn-sm btn-warning" runat="server" href="~/VerMas">Ver más</a>
                            <button class="btn btn-sm btn-success" onclick = "btnLogin">Añadir al carrito</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100">
                    <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="Corsair Vengeance 16GB DDR4">
                    <div class="card-body">
                        <h5 class="card-title">Corsair Vengeance 16GB DDR4</h5>
                        <p class="card-text">Memoria RAM de alta velocidad.</p>
                        <div class="d-flex gap-2">
                            <span class="badge bg-secondary">Memorias RAM</span>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between align-items-center">
                        <strong class="text-primary">$79.99</strong>
                        <div class="d-flex flex-row justify-content-end align-item-center gap-2">
                            <a class="btn btn-sm btn-warning" runat="server" href="~/VerMas">Ver más</a>
                            <button class="btn btn-sm btn-success" onclick = "btnLogin">Añadir al carrito</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100">
                    <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="Samsung 970 EVO 1TB SSD">
                    <div class="card-body">
                        <h5 class="card-title">Samsung 970 EVO 1TB SSD</h5>
                        <p class="card-text">Disco sólido NVMe ultrarrápido.</p>
                        <div class="d-flex gap-2">
                            <span class="badge bg-secondary">Almacenamiento</span>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between align-items-center">
                        <strong class="text-primary">$129.90</strong>
                        <div class="d-flex flex-row justify-content-end align-item-center gap-2">
                            <a class="btn btn-sm btn-warning" runat="server" href="~/VerMas">Ver más</a>
                            <button class="btn btn-sm btn-success" onclick = "btnLogin">Añadir al carrito</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="card h-100">
                    <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="Mouse Logitech G502">
                    <div class="card-body">
                        <h5 class="card-title">Mouse Logitech G502</h5>
                        <p class="card-text">Mouse gamer con alta precisión.</p>
                        <div class="d-flex gap-2">
                            <span class="badge bg-secondary">Periféricos</span>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between align-items-center">
                        <strong class="text-primary">$49.99</strong>
                        <div class="d-flex flex-row justify-content-end align-item-center gap-2">
                            <a class="btn btn-sm btn-warning" runat="server" href="~/VerMas">Ver más</a>
                            <button class="btn btn-sm btn-success" onclick = "btnLogin">Añadir al carrito</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" tabindex="-1"  aria-hidden="true" id="form-modal-login">
         <div class="modal-dialog modal-lg modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">LOGUEATE IMBÉCIL</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <div class="d-flex justify-content-center align-items-center vh-100 bg-dark">
					           <div class="card-body p-5">
						           <div class="text-center mb-4">
							           <span class="fw-bold" style="color: black; font-size: calc(1.5rem + 0.3vw);">COMPU</span>
							           <span class="fw-bold" style="color: #EB484C; font-size: calc(1.5rem + 0.3vw);">RANGERS</span>
						           </div>

						           <h5 class="text-center mb-4">Inicia sesión en tu cuenta</h5>

						           <form method="POST">
							           <div class="mb-3">
								           <label for="email" class="form-label">Correo electrónico</label>
								           <input type="email" class="form-control" id="email" name="email" placeholder="Introduce tu correo" required>
							           </div>

							           <div class="mb-3">
								           <label for="password" class="form-label">Contraseña</label>
								           <input type="password" class="form-control" id="password" name="password" placeholder="Introduce tu contraseña" required>
							           </div>

							           <div class="d-flex justify-content-between mb-3">
								           <div class="form-check">
									           <input class="form-check-input" type="checkbox" id="remember" name="remember">
									           <label class="form-check-label" for="remember">
										           Recordarme
									           </label>
								           </div>
								           <a href="#" class="text-decoration-none small" style="color: #EB484C;">¿Olvidaste tu contraseña?</a>
							           </div>

							           <button type="submit" class="btn btn-danger w-100 py-2">Iniciar sesión</button>
						           </form>

						           <div class="mt-3 text-center">
							           <p class="small">¿No tienes cuenta? <a href="#" class="text-decoration-none" style="color: #EB484C;">Regístrate aquí</a></p>
						           </div>
					           </div>
				        </div>
                    </div>
                    <div class="modal-footer d-flex gap-4">
                        <h5 class="mb-3">Total del Carrito: <strong>$829.97</strong></h5>
                        <button class="btn btn-success">Proceder al Pago</button>
                    </div>
                </div>
            </div>
        </div>
    </main>

</asp:Content>
