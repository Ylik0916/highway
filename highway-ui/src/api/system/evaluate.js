import request from '@/utils/request'

// 查询从业单位评价列表
export function listEvaluate(query) {
  return request({
    url: '/system/evaluate/scoreList',
    method: 'get',
    params: query
  })
}

// 查询从业单位评价详细
export function getEvaluate(unitId) {
  return request({
    url: '/system/evaluate/' + unitId,
    method: 'get'
  })
}

// 新增从业单位评价
export function addEvaluate(data) {
  return request({
    url: '/system/evaluate',
    method: 'post',
    data: data
  })
}

// 修改从业单位评价
export function updateEvaluate(data) {
  return request({
    url: '/system/evaluate',
    method: 'put',
    data: data
  })
}

// 删除从业单位评价
export function delEvaluate(unitId) {
  return request({
    url: '/system/evaluate/' + unitId,
    method: 'delete'
  })
}

// 查询从业单位及人员历史评价详细
export function getHistoryUnitList(evaluateUnitId) {
  return request({
    url: '/system/historyEvaluate/historyUnitList/' + evaluateUnitId,
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
