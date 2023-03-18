import request from '@/utils/request'

// 查询年度计划列表
export function listPlan(query) {
  return request({
    url: '/system/plan/list',
    method: 'get',
    params: query
  })
}

// 查询等待上报明细列表
export function listItemAnd0(query) {
  return request({
    url: '/system/item/listAnd0',
    method: 'get',
    params: query
  })
}


// 查询等待审核明细列表
export function listItemAnd1(query) {
  return request({
    url: '/system/item/listAnd1',
    method: 'get',
    params: query
  })
}



// 查询已驳回明细上报列表
export function listItemAnd2(query) {
  return request({
    url: '/system/item/listAnd2',
    method: 'get',
    params: query
  })
}



// 查询已审核明细列表
export function listItemAnd3(query) {
  return request({
    url: '/system/item/listAnd3',
    method: 'get',
    params: query
  })
}



// 查询年度计划详细
export function getPlan(id) {
  return request({
    url: '/system/plan/' + id,
    method: 'get'
  })
}

// 新增年度计划
export function addPlan(data) {
  return request({
    url: '/system/plan',
    method: 'post',
    data: data
  })
}

// 修改年度计划
export function updatePlan(data) {
  return request({
    url: '/system/plan',
    method: 'put',
    data: data
  })
}

// 删除年度计划
export function delPlan(id) {
  return request({
    url: '/system/plan/' + id,
    method: 'delete'
  })
}
