angular.module('registercibiogas')
    .service('Usuario',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/usuarios/:id', {}, {
                getAll: {method: 'GET', url: BaseUrl + '/usuarios', isArray: true},
                update: {method: 'PUT', url: BaseUrl + '/usuarios/:id', isArray: false},
                getFiltroUsuario: {method: 'GET', url: BaseUrl + '/usuarios/filtro/:filtro', isArray: true}
            });
        }])
    .service('Pais',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/paises/:id', {}, {
            getAll: {method: 'GET', url: BaseUrl + '/paises', isArray: true}
          });
        }]);