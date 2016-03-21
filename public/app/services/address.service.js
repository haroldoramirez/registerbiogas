angular.module('registercibiogas')
    .service('Usuario',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/usuario/:id', {}, {
                getAll: {method: 'GET', url: BaseUrl + '/usuarios', isArray: true},
                update: {method: 'PUT', url: BaseUrl + '/usuario/:id', isArray: false},
                getFiltroUsuario: {method: 'GET', url: BaseUrl + '/usuario/filtro/:filtro', isArray: true}
            });
        }])
    .service('Pais',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/pais/:id', {}, {
                getAll: {method: 'GET', url: BaseUrl + '/paises', isArray: true}
          });
        }])
    .service('Inscricao',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/inscricao/:id', {}, {
                getAll: {method: 'GET', url: BaseUrl + '/inscricoes', isArray: true}
          });
        }]);