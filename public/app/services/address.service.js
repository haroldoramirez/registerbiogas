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
    .service('Estado',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/estado/:id', {}, {
               getAll: {method: 'GET', url: BaseUrl + '/estados', isArray: true},
               update: {method: 'PUT', url: BaseUrl + '/estado/:id', isArray: false},
               getFiltroEstado: {method: 'GET', url: BaseUrl + '/estado/filtro/:filtro', isArray: true}
            });
        }])
    .service('Cidade',['$resource', 'BaseUrl',
          function($resource, BaseUrl){
            return $resource(BaseUrl + '/cidade/:id', {}, {
               getAll: {method: 'GET', url: BaseUrl + '/cidades', isArray: true},
               update: {method: 'PUT', url: BaseUrl + '/cidade/:id', isArray: false},
               getFiltroCidade: {method: 'GET', url: BaseUrl + '/cidade/filtro/:filtro', isArray: true}
            });
        }])
    .service('Inscricao',['$resource', 'BaseUrl',
        function($resource, BaseUrl){
            return $resource(BaseUrl + '/inscricao/:id', {}, {
                getAll: {method: 'GET', url: BaseUrl + '/inscricoes', isArray: true},
                getEstadoByIdPais: {method: 'GET', params:{idPais:'idPais'}, url: BaseUrl + '/estados/pais/:idPais', isArray: true},
                getCidadeByIdEstado: {method: 'GET', params:{idEstado:'idEstado'}, url: BaseUrl + '/cidades/estado/:idEstado', isArray: true}
          });
        }]);