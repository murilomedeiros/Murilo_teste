<%-- 
    Document   : paginaUsuario
    Created on : 22/10/2017, 23:10:52
    Author     : murilo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="pageModule">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Motoristas</title>
        <link rel='icon' href='resources/img/logo2.png'>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body id="pag" ng-controller="controllerDriver">
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>
        <div id="loader" class="loader"></div>
        <h1 class="text-center whiteC quiz-title">Cadastro de Motoristas</h1><br>
        <section class='container-user container-fluid content'>
            <h2 class="text-center">Formulário</h2><hr class='bottom-line3'><br><br>

            <!-- <form>
             <input type="submit" name="logoutUsuario" value="Sair"/> 
              <input type="submit" name="realizarQuiz" value="Realizar"/>
          </form>-->   
            <div class="content-user row">
                <div class="col-md-12">
                    <form>
                        <ng-form name="driverFrom">
                            <div class="row">             
                                <div class="form-group">
                                    <div class="col-md-12" > 
                                        <label for="name">Nome:<span>*</span></label>
                                        <input  ng-model="driverName" type="text" class="form-control" id="nameD" placeholder="João Frederico Fernandes" name="name" ng-required="true">
                                    </div>
                                </div>
                            </div><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-6" > 
                                        <label for="date">Data de nascimento:<span>*</span></label>
                                        <input ng-model="driverDate"   max="1999-12-31" required  type="date" class="form-control " id="dateD" ng-required="true">  
                                    </div>                                    
                                    <div class="col-md-6"> 
                                        <label for="cpf">CPF:<span>*</span></label>
                                        <input ng-model="driverCPF"  type="text" class="form-control" id="cpfD" placeholder="567.364.214-78" name="cpf" ng-required="true">
                                    </div>  
                                </div>
                            </div><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-6" > 
                                        <label for="date">Modelo do carro:<span>*</span></label>
                                        <input ng-model="driverCar"  type="text" class="form-control" id="carD" placeholder="" name="car" ng-required="true"> 
                                    </div>  
                                    <div class="col-md-6" > 
                                        <label for="car">Sexo:<span>*</span></label>
                                        <input ng-model="driverGender"  type="text" class="form-control" id="genderD" placeholder="" name="car" ng-required="true"> 
                                    </div>  
                                </div>
                            </div><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-6"> 
                                        <label for="gradu">Status:<span>*</span></label><br>
                                        <div class="col-md-3"> 
                                            <label><input ng-model="driverStatus" ng-value="ativo" name="status" type="radio" checked> Ativo</label><br>
                                            <label><input ng-model="driverStatus" ng-value="inativo"  name="status" type="radio"> Inativo</label>
                                        </div>
                                    </div> 
                                </div>
                            </div><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-3 col-md-offset-5">
                                        <button type="button" class="btn btn-primary" ng-click="addDriver(driverName, driverDate, driverCPF, driverCar, driverGender, driverStatus)" ng-disabled="driverFrom.$invalid">Adicionar</button>
                                    </div> 
                                </div>
                            </div><br>
                        </ng-form>
                    </form>                    
                </div>
            </div>

        </section>
        <%@include file="WEB-INF/jspf/success-register.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>

    </body>
</html>
