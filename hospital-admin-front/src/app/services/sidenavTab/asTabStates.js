/**
 * 注册表，登记了哪些 state 要作为tab 打开。第一个是主页。
 *
 * {
 *      title           : "",       // 标签页的标题
 *      matchStates     : "",       // 匹配的状态, FIXME 当前使用的状态的前缀，是否可以换成正则表达式？
 *      uiView          : "",       // 要追加的 ui-view 的名称
 *      curState        : "",       // 该标签页的内容会在多个状态之间切换时，当前实际要显示的状态。
 *      disableClose    : false,    // 是否禁止关闭。
 * }
 */
export default [
    {
        title: "首页",
        matchStates: "main.hospital.home",
        uiView: "home",
        curState: "main.hospital.home",
        disableClose: true
    },
    {
        name: '活动图文',
        matchStates: "main.hospital.articleList",
        uiView: "articleList",
        curState: "main.hospital.articleList",
        disableClose: true
    },
    {
        name: '活动图文新增',
        matchStates: "main.hospital.articleAdd",
        uiView: "articleAdd",
        curState: "main.hospital.articleAdd",
        disableClose: true
    },
    {
        name: '医生管理',
        matchStates: "main.hospital.doctor",
        uiView: "doctor",
        curState: "main.hospital.doctor",
        disableClose: true
    }

];
