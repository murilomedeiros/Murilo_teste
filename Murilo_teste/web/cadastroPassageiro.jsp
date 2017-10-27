<%-- 
    Document   : cadastroPassageiro
    Created on : 22/10/2017, 23:13:45
    Author     : murilo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="pageModule">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Passageiros</title>
        <link rel='icon' href='resources/img/logo2.png'>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
        <style>

        </style>
    </head>
    <body id="pag" ng-controller="controllerClient">
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>
        <div id="loader" class="loader"></div>
        <h1 class="text-center whiteC quiz-title">Cadastro de Passageiros</h1><br>
        <section class='container-user container-fluid content'>
            <h2 class="text-center">Formulário</h2><hr class='bottom-line3'><br><br>

            <div class="content-user row">
                <div class="col-md-12">
                    <form>
                        <ng-form name="clientForm">
                            <div class="row">             
                                <div class="form-group">
                                    <div class="col-md-12" > 
                                        <label for="name">Nome:<span>*</span></label>
                                        <input  ng-model="clientName" type="text" class="form-control" id="name" placeholder="João Frederico Fernandes" ng-required="true">
                                    </div>
                                </div>
                            </div><br><br>
                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-4" > 
                                        <label for="date">Data de nascimento:<span>*</span></label>
                                        <input ng-model="clientDate"  max="1999-12-31" required  type="date" class="form-control " id="date" ng-required="true">  
                                    </div>                                    
                                    <div class="col-md-4"> 
                                        <label for="cpf">CPF:<span>*</span></label>
                                        <input ng-model="clientCPF" type="text" class="form-control cpf" id="cpf" placeholder="567.364.214-78" name="cpf" ng-required="true">
                                    </div>
                                    <div class="col-md-4" > 
                                        <label for="gender">Sexo:<span>*</span></label>
                                        <input ng-model="clientGender" type="text" class="form-control" id="car" placeholder="" name="car" ng-required="true"> 
                                    </div>  
                                </div>
                            </div><br><br>

                            <div class="row">             
                                <div class="form-group">  
                                    <div class="col-md-3 col-md-offset-5">
                                        <button type="button" class="btn btn-primary" ng-click="addClient(clientName, clientCPF, clientDate, clientGender)" ng-disabled="clientForm.$invalid">Adicionar</button>
                                    </div> 
                                </div>
                            </div><br>
                        </ng-form>
                    </form>                    
                </div>
            </div>
        </section>
        <%@include file="WEB-INF/jspf/success-register.jspf" %>
        <%@include file="WEB-INF/jspf/erro-modal.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <!-- Jquery Mask -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <!-- Custom Js -->
        <script src="resources/js/custom.js" type="text/javascript"></script>

    </body>
</html>
