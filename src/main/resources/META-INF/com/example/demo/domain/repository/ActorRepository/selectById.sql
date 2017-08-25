select
  a.id as id
  , a.name as name
  , a.height as height
  , a.blood as blood
  , a.birthday as birthday
  , p.id as prefecture_id
  , p.name as prefecture_name
  , a.update_at as update_at
from
  actor as a
  left join prefecture as p
    on a.birthplace_id = p.id
where
  a.id = /* id */1
order by
  a.id