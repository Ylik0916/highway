import request from '@/utils/request'

// 查询从业人员评价列表
export function listPerson(query) {
  return request({
    url: '/system/person/list',
    method: 'get',
    params: query
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
