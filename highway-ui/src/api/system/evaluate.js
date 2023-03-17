import request from '@/utils/request'

// 查询从业单位评价列表
export function listEvaluate(query) {
  return request({
    url: '/system/evaluate/list',
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
