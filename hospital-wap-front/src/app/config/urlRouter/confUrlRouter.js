confUrlRouter.$inject = ['$urlRouterProvider'];
function confUrlRouter($urlRouterProvider) {
    $urlRouterProvider.otherwise('/hospital/home');
}


export default confUrlRouter;
