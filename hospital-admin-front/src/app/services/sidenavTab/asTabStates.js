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
        title: "应用信息",
        matchStates: "main.hospital.home",
        uiView: "home",
        curState: "main.hospital.home"
    },
    {
        title: "应用信息编辑",
        matchStates: "main.hospital.home",
        uiView: "home",
        curState: "main.hospital.home"
    },
    {
        title: "应用角色",
        matchStates: "main.hospital.home",
        uiView: "home",
        curState: "main.hospital.home"
    },
    
];
