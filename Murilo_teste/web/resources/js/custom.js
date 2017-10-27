$(document).on("scroll", function () {
    if ($(document).scrollTop() > 100) { //QUANDO O SCROLL PASSAR DOS 100px DO TOPO
        $("#navbarP").removeClass("big"); //TROCA P CLASSE MENOR
    } else {
        $("#navbarP").addClass("big");//VOLTA P MENU GRANDE ORIGINAL
    }
});


$(document).ready(function () {
    $('.data').mask('00/00/0000');
    $('.cep').mask('00000-000');
    $('.telefone').mask('(00) 0000-00000');
    $('.cpf').mask('000.000.000-00', {reverse: true});
    $('.cnpj').mask('00.000.000/0000-00', {reverse: true});
    $('.rg').mask('00.000.000-0', {reverse: true});
});

$(document).ready(function () {    
    $('#search-driver').keyup(function () {
        searchDriver($(this).val(), $('#filter-driver').val());
    });
    function searchDriver(value, param) {
        $('#table-drvrs tbody').find('tr').each(function () {
            var conteudoCelula = $(this).find(param).text();
            var corresponde = conteudoCelula.toLowerCase().indexOf(value) >= 0;
            $(this).css('display', corresponde ? '' : 'none');
        });
    }

    $('#search-client').keyup(function () {
        searchConfirmedEvent($(this).val(), $('#filter-client').val());
    });
    function searchConfirmedEvent(value, param) {
        $('#table-clnt tbody').find('tr').each(function () {
            var conteudoCelula = $(this).find(param).text();
            var corresponde = conteudoCelula.toLowerCase().indexOf(value) >= 0;
            $(this).css('display', corresponde ? '' : 'none');
        });
    }
});