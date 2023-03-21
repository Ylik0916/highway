import request from '@/utils/request'

// 查询从业人员评价列表新版
export function listPerson(queryParams, hwUnitEvaluate) {
  return request({
    url: '/system/person/newList',
    method: 'get',
    params: queryParams, hwUnitEvaluate
  })
}

// 查询从业人员评价详细
export function getPerson(personId) {
  return request({
    url: '/system/person/' + personId,
    method: 'get'
  })
}

// 新增从业人员评价
export function addPerson(data) {
  return request({
    url: '/system/person',
    method: 'post',
    data: data
  })
}

// 修改从业人员评价
export function updatePerson(data) {
  return request({
    url: '/system/person',
    method: 'put',
    data: data
  })
}

// 删除从业人员评价
export function delPerson(personId) {
  return request({
    url: '/system/person/' + personId,
    method: 'delete'
  })
}

// 查询从业单位评价列表
export function listEvaluate(query) {
  return request({
    url: '/system/evaluate/scoreList',
    method: 'get',
    params: query
  })
}

// 查询从业单位及人员历史评价详细
export function getHistoryPersonList(evaluatePersonId) {
  return request({
    url: '/system/historyEvaluate/historyPersonList/' + evaluatePersonId,
    method: 'get'
  })
}

// 新增从业单位及人员历史评价添加
export function addHistory(data) {
  return request({
    url: '/system/historyEvaluate',
    method: 'post',
    data: data
  })
}

// 查询从业单位及人员历史评价详细
export function getHistory(evaluateId) {
  return request({
    url: '/system/historyEvaluate/' + evaluateId,
    method: 'get'
  })
}

// 修改从业单位及人员历史评价修改
export function updateHistory(data) {
  return request({
    url: '/system/historyEvaluate',
    method: 'put',
    data: data
  })
}

// 删除从业单位及人员历史评价删除
export function delHistory(evaluateId) {
  return request({
    url: '/system/historyEvaluate/' + evaluateId,
    method: 'delete'
  })
}
