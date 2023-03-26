import request from '@/utils/request'


// 根据年度计划Id查询进度列表
export function listItemByPlanId(id) {
  return request({
    url: '/system/item/listByPlanId/' + id,
    method: 'get',
  })
}

// 查询进度列表
export function listItem(query) {
  return request({
    url: '/system/item/list',
    method: 'get',
    params: query
  })
}

// 查询进度详细
export function getItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'get'
  })
}

// 新增进度
export function addItem(data) {
  return request({
    url: '/system/item',
    method: 'post',
    data: data
  })
}

// 修改进度
export function updateItem(data) {
  return request({
    url: '/system/item',
    method: 'put',
    data: data
  })
}

// 修改进度状态为已审核
export function updateItemScale(id) {
  return request({
    url: '/system/item/scale/' + id,
    method: 'put',
  })
}


// 修改进度为已驳回
export function rejectScale(id) {
  return request({
    url: '/system/item/reject/' + id,
    method: 'put',
  })
}

// 删除进度
export function delItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'delete'
  })
}
