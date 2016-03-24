angular.module('registerbiogas')
  .controller('CidadeCreateController', function ($scope, $location, Cidade, Estado, toastr) {

    $scope.init = function() {
        Estado.getAll(function(data) {
            $scope.estados = data;
        });
    };

    $scope.save = function() {
        Cidade.save($scope.cidade, function(data) {
            toastr.success('foi salva com Sucesso.', 'A cidade: ' + $scope.cidade.nome);
            $location.path('/cidades');
        }, function(data) {
            toastr.error(data.data, 'Não foi possível Salvar.');
        });
    };

    $scope.cancel = function() {
        $location.path('/cidades');
    };

  }).controller('CidadeListController', function ($scope, Cidade, toastr, $routeParams) {

    $scope.init = function() {
        $scope.nomeFiltro = '';

        Cidade.getAll(function(data) {
           $scope.cidades = data;
           $scope.quantidade = $scope.cidades.length;
        });
    };

    $scope.busca = function() {

       if ($scope.nomeFiltro) {
            Cidade.getFiltroCidade({filtro:$scope.nomeFiltro}, $scope.cidade, function(data) {
                $scope.cidades = data;
            });
       } else {
            Cidade.getAll(function(data) {
                $scope.cidades = data;
            });
       };
    };

  }).controller('CidadeDetailController', function ($scope, $modal, $routeParams, $location, Cidade, Estado, toastr) {

    $scope.init = function() {
        $scope.cidade = Cidade.get({id:$routeParams.id}, function(data) {
        $scope.estados = Estado.getAll();
        },function(data) {
            toastr.error(data.data);
        });
    };

    $scope.delete = function() {
        $scope.cidade = Cidade.get({id:$routeParams.id}, function(data) {
            $scope.cidadeExcluida = $scope.cidade.nome;
        });
        Cidade.delete({id:$routeParams.id}, function() {;
            toastr.warning('foi removida com Sucesso.', 'A cidade: ' + $scope.cidadeExcluida);
            $modalInstance.close();
            $location.path('/cidades');
        }, function(data) {
            $modalInstance.close();
            toastr.error(data.data, 'Não foi possível Remover.');
        });
    };

    $scope.open = function (size) {

        $modalInstance = $modal.open({
              templateUrl: 'modalConfirmacao.html',
              controller: 'CidadeDetailController',
              size: size,
        });
    };

    $scope.cancelModal = function () {
        $modalInstance.dismiss('cancelModal');
    };

  }).controller('CidadeEditController', function ($scope, $modal, $routeParams, $location, Cidade, Estado, toastr) {


    $scope.init = function() {
        $scope.cidade = Cidade.get({id:$routeParams.id}, function(data) {
        $scope.estados = Estado.getAll();
        },function(data) {
            toastr.error(data.data);
        });
    };

    $scope.update = function() {
        Cidade.update({id:$routeParams.id}, $scope.cidade, function(data) {
            toastr.info('foi atualizada com Sucesso.', 'A cidade: ' + $scope.cidade.nome);
            $location.path('/cidades');
        },function(data) {
           toastr.error(data.data, 'Não foi possível Atualizar.');
        });
    };

  }).controller('modal.cidade.controller', function ($scope, $uibModal, $log) {

          $scope.items = ['item1', 'item2', 'item3'];

          $scope.animationsEnabled = true;

          $scope.open = function (size) {

            var modalInstance = $uibModal.open({
              animation: $scope.animationsEnabled,
              templateUrl: 'modalCidade.html',
              controller: 'modalInstance.cidade.controller',
              size: size,
              resolve: {
                items: function () {
                  return $scope.items;
                }
              }
            });

            modalInstance.result.then(function (selectedItem) {
              $scope.selected = selectedItem;
            }, function () {
              $log.info('Modal dismissed at: ' + new Date());
            });
          };

          $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
          };

        }).controller('modalInstance.cidade.controller', function ($scope, $uibModalInstance, items) {

        // Please note that $uibModalInstance represents a modal window (instance) dependency.
        // It is not the same as the $uibModal service used above.

          $scope.items = items;
          $scope.selected = {
            item: $scope.items[0]
          };

          $scope.ok = function () {
            $uibModalInstance.close($scope.selected.item);
          };

          $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
          };
        });