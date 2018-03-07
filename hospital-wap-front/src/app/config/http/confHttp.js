function confHttp($httpProvider) {
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.interceptors.push('normalizeHttpResp');
    $httpProvider.defaults.withCredentials = true;
}
confHttp.$inject = ['$httpProvider'];

export default confHttp;
