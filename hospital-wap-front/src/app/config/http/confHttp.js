function confHttp($httpProvider) {
    $httpProvider.defaults.withCredentials = true;
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.interceptors.push('normalizeHttpResp');
}
confHttp.$inject = ['$httpProvider'];

export default confHttp;
