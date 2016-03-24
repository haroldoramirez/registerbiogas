angular.module('registerbiogas')
  .controller('inscricao.controller', function ($scope, Pais, Estado, Cidade, toastr) {
    console.log('Controller Inscrito');

      $scope.init = function() {
          Pais.getAll(function(data) {
              $scope.paises = data;
          });
          Estado.getAll(function(data) {
             $scope.estados = data;
          });
          Cidade.getAll(function(data) {
             $scope.cidades = data;
          });
      };

      //lógica para ativar o input quando for selecionado o radio button Outro:
      $scope.radioValue = function(value) {
        $scope.ativado = value;

        if ($scope.ativado == 'outros') {
            $scope.ativaInput = true;
        } else {
            $scope.ativaInput = false;
            $scope.inscrito.observacoes = '';
        }
      }
});