<%-- 
    Document   : cadastroCorrida
    Created on : 23/10/2017, 21:54:33
    Author     : murilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="pageModule">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Corridas</title>
        <link rel='icon' href='resources/img/logo2.png'>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body id="pag" ng-controller="controllerRun">
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>
        <div id="loader" class="loader"></div>
        <h1 class="text-center whiteC quiz-title">Cadastro de Corridas</h1><br>
        <section class='container-user container-fluid content'>
            <h2 class="text-center">Formulário</h2><hr class='bottom-line3'><br><br>

            <!-- <form>
             <input type="submit" name="logoutUsuario" value="Sair"/> 
              <input type="submit" name="realizarQuiz" value="Realizar"/>
          </form>-->   
            <div class="content-user row">
                <div class="col-md-12">
                    <form>
                        <ng-form name="runFrom">
                            <div class="row">             
                                <div class="form-group">
                                    <div class="col-md-6" > 
                                        <label for="name">Nome do Motorista:<span>*</span></label>
                                        <input ng-model="driverNameR" type="text" class="form-control" id="dName" placeholder="João Frederico Fernandes"  ng-required="true">
                                    </div>
                                    <div class="col-md-6"> 
                                        <label for="name">Nome do Passageiro:<span>*</span></label>
                                        <input ng-model="clientNameR" type="text" class="form-control" id="cName" placeholder="Paula Frederico Fernandes"  ng-required="true">
                                    </div>  
                                </div>
                            </div><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-6" > 
                                        <label for="date">Valor da Corrida:<span>*</span></label>
                                        <input ng-model="priceR" type="text" class="form-control" id="price" placeholder=""  ng-required="true"> 
                                    </div>  
                                </div>
                            </div><br><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-3 col-md-offset-5">
                                        <button  ng-disabled="runFrom.$invalid" type="button"class="btn btn-primary" ng-click="addRun(driverNameR, clientNameR, priceR)">Adicionar</button>
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