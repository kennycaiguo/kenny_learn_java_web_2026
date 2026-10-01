const KEY='dept_admin_data';
const DEFAULT_DEPTS=[{id:1,name:'技术部',leader:'张伟',parent:'—',phone:'010-88881111',status:'启用',description:'负责公司技术研发、系统建设与技术支持。'},{id:2,name:'市场部',leader:'李娜',parent:'—',phone:'010-88882222',status:'启用',description:'负责市场推广、品牌建设与客户拓展。'},{id:3,name:'人事部',leader:'王芳',parent:'—',phone:'010-88883333',status:'启用',description:'负责招聘、培训、绩效与员工关系。'},{id:4,name:'研发一组',leader:'赵强',parent:'技术部',phone:'010-88884444',status:'停用',description:'技术部下属研发团队。'}];
function getDepts(){const raw=localStorage.getItem(KEY);if(!raw){saveDepts(DEFAULT_DEPTS);return DEFAULT_DEPTS.slice()}try{return JSON.parse(raw)}catch(e){return DEFAULT_DEPTS.slice()}}
function saveDepts(data){localStorage.setItem(KEY,JSON.stringify(data))}
